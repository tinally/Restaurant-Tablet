package tests;

import kitchen.Chef;
import kitchen.Server;
import org.junit.Assert;
import org.junit.Test;
import restaurant.Table;
import services.BillPrinterService;
import services.KitchenFactoryService;
import services.framework.Service;
import services.framework.ServiceConstructor;
import services.framework.ServiceContainer;

import java.util.Stack;

public class ServiceTests {

  @Test
  public void testKitchenFactory() {
    ServiceContainer container = new ServiceContainer();
    KitchenFactoryService kitchen = container.getInstance(KitchenFactoryService.class);

    Server bob = kitchen.createServer("Bob", new Table(15, 10));
    Chef joe = kitchen.createChef("Joe");
    Assert.assertNotNull(bob);
    Assert.assertNotNull(joe);
  }
  @Test
  public void testInstantiateParameterlessService() {
    ServiceContainer container = new ServiceContainer();
    BillPrinterService bps = container.getInstance(BillPrinterService.class);
    Assert.assertNotNull(bps);
  }

  @Test
  public void testInstantiateDependentService() {
    ServiceContainer container = new ServiceContainer();
    DependentService dps = container.getInstance(DependentService.class);
    Assert.assertNotNull(dps);
    Assert.assertTrue(dps.dependentService == container.getInstance(BillPrinterService.class));
  }

  @Test
  public void testFailInstantiateNonService() {
    ServiceContainer container = new ServiceContainer();
    DependentManuallyInstantiatedService dps = container.getInstance(DependentManuallyInstantiatedService.class);
    Assert.assertNull(dps);
  }

  @Test(expected = StackOverflowError.class)
  public void testFailRecursiveDependency() {
    ServiceContainer container = new ServiceContainer();
    RecursiveDependentServiceOne dps = container.getInstance(RecursiveDependentServiceOne.class);
  }
}

class DependentService extends Service {
  BillPrinterService dependentService;
  @ServiceConstructor
  public DependentService(BillPrinterService dependentService) {
    Assert.assertNotNull(dependentService);
    this.dependentService = dependentService;
  }
}


class RecursiveDependentServiceOne extends Service {
  @ServiceConstructor
  public RecursiveDependentServiceOne(RecursiveDependentServiceTwo dependency) {

  }
}

class RecursiveDependentServiceTwo extends Service {
  @ServiceConstructor
  public RecursiveDependentServiceTwo(RecursiveDependentServiceOne dependency) {

  }
}

class DependentManuallyInstantiatedService extends Service {
  BillPrinterService dependentService;
  @ServiceConstructor
  public DependentManuallyInstantiatedService(BillPrinterService dependentService, int integer) {
    Assert.assertNotNull(dependentService);
    this.dependentService = dependentService;
  }
}
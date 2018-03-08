package tests;

import edu.toronto.csc207.restaurantsolution.model.Chef;
import edu.toronto.csc207.restaurantsolution.model.Server;
import edu.toronto.csc207.restaurantsolution.services.PaymentService;
import org.junit.Assert;
import org.junit.Test;
import edu.toronto.csc207.restaurantsolution.model.Table;
import edu.toronto.csc207.restaurantsolution.services.KitchenFactoryService;
import edu.toronto.csc207.restaurantsolution.framework.services.Service;
import edu.toronto.csc207.restaurantsolution.framework.services.ServiceConstructor;
import edu.toronto.csc207.restaurantsolution.framework.services.ServiceContainer;

public class ServiceTests {

  @Test
  public void testKitchenFactory() {
    ServiceContainer container = new ServiceContainer();
    KitchenFactoryService kitchen = container.getInstance(KitchenFactoryService.class);

    Server bob = kitchen.createServer("Bob", 15);
    Chef joe = kitchen.createChef("Joe");
    Assert.assertNotNull(bob);
    Assert.assertNotNull(joe);
  }
  @Test
  public void testInstantiateParameterlessService() {
    ServiceContainer container = new ServiceContainer();
    PaymentService bps = container.getInstance(PaymentService.class);
    Assert.assertNotNull(bps);
  }

  @Test
  public void testInstantiateDependentService() {
    ServiceContainer container = new ServiceContainer();
    DependentService dps = container.getInstance(DependentService.class);
    Assert.assertNotNull(dps);
    Assert.assertTrue(dps.dependentService == container.getInstance(PaymentService.class));
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
  PaymentService dependentService;
  @ServiceConstructor
  public DependentService(PaymentService dependentService) {
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
  PaymentService dependentService;
  @ServiceConstructor
  public DependentManuallyInstantiatedService(PaymentService dependentService, int integer) {
    Assert.assertNotNull(dependentService);
    this.dependentService = dependentService;
  }
}
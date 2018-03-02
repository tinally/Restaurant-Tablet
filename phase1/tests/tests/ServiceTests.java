package tests;

import org.junit.Assert;
import org.junit.Test;
import services.BillPrinterService;
import services.framework.Service;
import services.framework.ServiceConstructor;
import services.framework.ServiceContainer;

public class ServiceTests {

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

}

class DependentService extends Service {
  BillPrinterService dependentService;
  @ServiceConstructor
  public DependentService(BillPrinterService dependentService) {
    Assert.assertNotNull(dependentService);
    this.dependentService = dependentService;
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
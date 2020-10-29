package com.udemy.multiinstance.tenant;

public class TenantAwareService {

    private static ThreadLocal<String> tenantContext = new ThreadLocal<>();

    public static String getTenant() {
        return tenantContext.get();
    }

    public static void setTenant(String tenant) {
        tenantContext.set(tenant);
    }
}

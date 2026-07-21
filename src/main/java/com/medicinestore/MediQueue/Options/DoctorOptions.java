package com.medicinestore.MediQueue.Options;

public enum DoctorOptions {
    DR_SHARMA("Dr. Sharma"),
    DR_MOHANTY("Dr. Mohanty"),
    DR_PATNAIK("Dr. Patnaik"),
    DR_DAS("Dr. Das"),
    DR_MISHRA("Dr. Mishra");

    private final String displayName;

    DoctorOptions(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

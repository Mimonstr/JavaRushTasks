package com.javarush.task.task39.task3911;

import java.util.*;

public class Software {
    private int currentVersion;

    private Map<Integer, String> versionHistoryMap = new LinkedHashMap<>();

    public void addNewVersion(int version, String description) {
        if (version > currentVersion) {
            versionHistoryMap.put(version, description);
            currentVersion = version;
        }
    }

    public int getCurrentVersion() {
        return currentVersion;
    }

    public Map<Integer, String> getVersionHistoryMap() {
        return Collections.unmodifiableMap(versionHistoryMap);
    }

    public boolean rollback(int rollbackVersion)
    {
        if(!versionHistoryMap.containsKey(rollbackVersion) || rollbackVersion > currentVersion) return false;
        else
        {
            while (currentVersion != rollbackVersion)
            {
                versionHistoryMap.remove(currentVersion--);
            }
            return true;
        }
    }
}

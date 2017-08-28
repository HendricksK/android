package com.s212037943.KurvinHendricks.databaseapplication.repository;

import com.s212037943.KurvinHendricks.databaseapplication.Settings;
import com.s212037943.KurvinHendricks.databaseapplication.User;

import java.util.List;

/**
 * Created by Home on 8/17/2014.
 */
public interface DatasourceDAO {

    // settings class methods being declared here
    public void createSettings(Settings settings);
    public void updateSettings(Settings settings);
    public Settings findSettingsByID(int id);
    public void deleteSettings(Settings settings);
    public Settings getSettings();
    public List<Settings> getSettingsList();

    // user class methods being declared here
    public void createUser(User user);
    public void updateUser(User user);
    public User findUserByID(int id);
    public void deleteUser(User user);
    public User getUser();
    public int getUserSize();
}

package com.zonal.starwars.presenter;

public interface StarWarsPresenter {
    int name_asc = 0;
    int name_desc = 1;
    void refreshList();
    void sortList(int sort);
    void getPlanet(int position);
}

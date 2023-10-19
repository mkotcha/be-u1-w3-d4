package emmek.dao;

import emmek.entities.Location;

public interface LocationDao {
    public void save(Location location);

    public Location getById(long id);

    public void delete(Location location);

    public void refresh(Location location);
}

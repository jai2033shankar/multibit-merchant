package org.multibit.mbm.client.interfaces.rest.api.representations.hal.item;

import com.google.common.base.Preconditions;
import com.theoryinpractise.halbuilder.api.Representation;
import org.multibit.mbm.client.common.pagination.PaginatedList;
import org.multibit.mbm.client.domain.model.model.Item;
import org.multibit.mbm.client.interfaces.rest.api.hal.Representations;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * <p>Representation to provide the following to {@link org.multibit.mbm.client.domain.model.model.Item}:</p>
 * <ul>
 * <li>Creates representation of multiple Items for the public</li>
 * </ul>
 *
 * @since 0.0.1
 */
public class PublicItemCollectionRepresentation {

  private final PublicItemRepresentation publicItemRepresentation = new PublicItemRepresentation();

  public Representation get(PaginatedList<Item> items) {

    Preconditions.checkNotNull(items, "items");

    URI self = UriBuilder.fromPath("/admin/item").build();
    Representation itemList = Representations.newPaginatedList(self, items);

    // Use the reduced public fields as embedded resources
    for (Item item : items.list()) {
      Representation itemRepresentation = publicItemRepresentation.get(item);
      itemList.withRepresentation("item", itemRepresentation);
    }

    return itemList;

  }

}

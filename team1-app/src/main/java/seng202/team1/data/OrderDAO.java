package seng202.team1.data;

import seng202.team1.model.Order;

import java.util.Set;

public interface OrderDAO {

    /**
     * returns all the Orders stored in the system.
     * @return a List of all Orders stored in the system
     */
    Set<Order> getAllOrders();

    /**
     * gets a single Order from the system.
     * @param ID the Order's unique code
     * @return the desired Order or null if not found
     */
    Order getOrderByID(int ID);

    /**
     * adds an Order to storage. the order will be stored using its code attribute.
     * @param order the Order to store
     */
    void addOrder(Order order);

    /**
     * sets the properties of an Order to those of a new Order.
     * the new Order's code should be the same as the one that is being updated.
     * @param alteredOrder the Order to update in storage
     */
    void updateOrder(Order alteredOrder);

    /**
     * remove an Order from storage.
     * @param ID the code of the Order to remove
     */
    void removeOrder(int ID);

}

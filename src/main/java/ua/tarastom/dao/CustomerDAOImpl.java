package ua.tarastom.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.tarastom.entity.Customer;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers() {
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        // create a query
        //add order by lastName
        Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName", Customer.class);
        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();
        // return the results
        return customers;
    }

    @Override
    public void saveOrUpdateCustomer(Customer theCustomer) {
        Session currentSession = sessionFactory.getCurrentSession();
        //берет id из скытого поля <form:hidden path="id"/> customer-form и сам определяет сохранять или обновлять
        currentSession.saveOrUpdate(theCustomer);
    }

    @Override
    public Customer getCustomer(int theId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Customer theCustomer = currentSession.get(Customer.class, theId);
        return theCustomer;
    }
}

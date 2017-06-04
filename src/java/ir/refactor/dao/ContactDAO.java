package ir.refactor.dao;

import ir.refactor.model.Contact;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 3tar on 3/27/2017.
 */
@Repository
public class ContactDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List getAllContacts(String searchText) {

        String hql = "SELECT * FROM Contact";
        if(searchText != "")
            hql += " WHERE FirstName LIKE ':firstName' OR LastName LIKE ':lastName' OR Phone LIKE ':phone'" ;

            Query query =sessionFactory.getCurrentSession().createQuery(hql);

        if(searchText != "") {
            query.setParameter("firstName", "%" + searchText + "%");
            query.setParameter("lastName", "%" + searchText + "%");
            query.setParameter("Phone", "%" + searchText + "%");
            }

        List contacts = query.list();
        return contacts;
    }

    public Contact getContact(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Contact contact = (Contact) session.load(Contact.class, new Integer(id));
        return contact;
    }

    public Contact addContact(Contact contact) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(contact);
        return contact;
    }

    public void updateContact(Contact country) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(country);
    }

    public void deleteContact(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Contact p = (Contact) session.load(Contact.class, new Integer(id));
        if (null != p) {
            session.delete(p);
        }
    }

    public void deleteAllContacts() {
        Session session = this.sessionFactory.getCurrentSession();
        String hql = String.format("delete from Contact");
        Query query = session.createQuery(hql);
        query.executeUpdate();
    }

    public boolean isContactExist(Contact contact) {

        return true;
    }

}

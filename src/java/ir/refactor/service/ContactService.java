package ir.refactor.service;

import java.util.ArrayList;
import java.util.List;

import ir.refactor.ViewModel.ContactViewModel;
import ir.refactor.dao.ContactDAO;
import ir.refactor.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("contactService")
public class ContactService {

    @Autowired
    ContactDAO contactDAO;

    @Autowired
    ContactConverter ContactConverter;

    @Transactional
    public List<ContactViewModel> getAllContacts(String searchText) {
        List<Contact> contacts= contactDAO.getAllContacts(searchText);

        List<ContactViewModel> viewModels = new ArrayList<ContactViewModel>(contacts.size());

        for (Contact contact : contacts )
        {
            viewModels.add(ContactConverter.contactToViewModel(contact));
        }

        return viewModels;
    }

    @Transactional
    public ContactViewModel getContact(int id) {

        return ContactConverter.contactToViewModel(contactDAO.getContact(id));
    }

    @Transactional
    public void addContact(ContactViewModel contact)
    {
        contactDAO.addContact(ContactConverter.viewModelToContact(contact));
    }

    @Transactional
    public void updateContact(ContactViewModel contact)
    {
        contactDAO.updateContact(ContactConverter.viewModelToContact(contact));
    }

    @Transactional
    public void deleteContact(int id)
    {
        contactDAO.deleteContact(id);
    }

    @Transactional
    public void deleteAllContacts() {
        contactDAO.deleteAllContacts();
    }
    public boolean isContactExist(ContactViewModel contact)
    {
        return contactDAO.isContactExist(ContactConverter.viewModelToContact(contact));
    }
}
package ir.refactor.service;

import ir.refactor.ViewModel.ContactViewModel;
import ir.refactor.model.Contact;

/**
 * Created by 3tar on 3/27/2017.
 */
public class ContactConverter {
    public ContactViewModel contactToViewModel(Contact contact)
    {
        return new ContactViewModel(contact.getId(),contact.getFirstName(),contact.getLastName(),contact.getPhone());
    }
    public Contact viewModelToContact(ContactViewModel contactViewModel){
        return new Contact(contactViewModel.getFirstName() , contactViewModel.getLastName() , contactViewModel.getPhone());
    }
}

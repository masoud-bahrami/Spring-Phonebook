package ir.refactor.controller;

import ir.refactor.ViewModel.ContactViewModel;
import ir.refactor.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by 3tar on 3/27/2017.
 */
@RestController
public class ContactController {

    @Autowired
    ContactService contactService;  //Service which will do all data retrieval/manipulation work


    //-------------------لیست کانتکت ها---------------

    @RequestMapping(value = "/contact/", method = RequestMethod.GET)
    public ResponseEntity<List<ContactViewModel>> listAllContacts(@PathVariable("searchText") String searchText) {
        List<ContactViewModel> contacts = contactService.getAllContacts(searchText);
        if(contacts.isEmpty()){
            return new ResponseEntity<List<ContactViewModel>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<ContactViewModel>>(contacts, HttpStatus.OK);
    }

    //---------------گت کردن یک کانتکت-----------------

    @RequestMapping(value = "/contact/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContactViewModel> getContact(@PathVariable("id") int id) {
        System.out.println("Fetching Contact with id " + id);
        ContactViewModel contact = contactService.getContact(id);
        if (contact == null) {
            System.out.println("Contact with id " + id + " not found");
            return new ResponseEntity<ContactViewModel>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ContactViewModel>(contact, HttpStatus.OK);
    }


    //-------------------افزودن کانتکت جدید-----------------------------------

    @RequestMapping(value = "/contact/", method = RequestMethod.POST)
    public ResponseEntity<Void> createContact(@RequestBody ContactViewModel contact, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Contact " + contact.getFirstName());

        contactService.updateContact(contact);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/contact/{id}").buildAndExpand(contact.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //------------------- ویرایش کانتکت ------------------

    @RequestMapping(value = "/contact/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ContactViewModel> updateContact(@PathVariable("id") int id, @RequestBody ContactViewModel contact) {
        System.out.println("Updating Contact " + id);

        ContactViewModel currentContact = contactService.getContact(id);

        if (currentContact==null) {
            System.out.println("Contact with id " + id + " not found");
            return new ResponseEntity<ContactViewModel>(HttpStatus.NOT_FOUND);
        }

        currentContact.setFirstName(contact.getFirstName());
        currentContact.setLastName(contact.getLastName());
        currentContact.setPhone(contact.getPhone());

        contactService.updateContact(currentContact);
        return new ResponseEntity<ContactViewModel>(currentContact, HttpStatus.OK);
    }



    //------------------- حذف یک کانتکت -----------------------

    @RequestMapping(value = "/contact/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ContactViewModel> deleteContact(@PathVariable("id") int id) {
        System.out.println("Fetching & Deleting Contact with id " + id);

        ContactViewModel contact = contactService.getContact(id);
        if (contact == null) {
            System.out.println("Unable to delete. Contact with id " + id + " not found");
            return new ResponseEntity<ContactViewModel>(HttpStatus.NOT_FOUND);
        }

        contactService.deleteContact(id);
        return new ResponseEntity<ContactViewModel>(HttpStatus.NO_CONTENT);
    }

    //------------------- حذف تمامی کانتکت ها---------------

    @RequestMapping(value = "/contact/", method = RequestMethod.DELETE)
    public ResponseEntity<ContactViewModel> deleteAllContacts() {
        System.out.println("Deleting All Contacts");

        contactService.deleteAllContacts();
        return new ResponseEntity<ContactViewModel>(HttpStatus.NO_CONTENT);
    }

}

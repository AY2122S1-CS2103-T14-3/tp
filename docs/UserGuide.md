---
layout: page
title: User Guide
---

MyCRM is a **desktop application for managing client contacts, repair job statuses, and product information that has
been optimised for use via a Command Line Interface (CLI)** while maintaining the benefits of a Graphical User Interface
(GUI). If you type quickly, MyCRM can complete customer relationship management tasks faster than traditional GUI
applications.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `MyCRM.jar` from [here](https://github.com/AY2122S1-CS2103-T14-3/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the
   app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it.<br>
   Some example commands you can try:

   * **`listContacts `** : Lists all contacts.

   * **`addContact `**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact
     named `John Doe` to the MyCRM Book.

   * **`deleteContact `**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence
  of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `listJobs`, `listProducts `, and `exit`
  ) will be ignored.<br>
  e.g. if the command specifies `listJobs 123`, it will be interpreted as `listJobs`.

</div>

### Adding a job: `addJob`

Adds a new repair job to the CRM.

Format: `addJob d/DESCRIPTION [c/CONTACT_INDEX] [p/PRODUCT_INDEX] [by/DELIVERY_DATE]`

* Creates a new repair job.
* Links the contact and product that correspond to `CONTACT_INDEX` and `PRODUCT_INDEX` (in the res
  respective contact and product list) to the job.

Examples:

* `addJob d/CPU replacement needed c/1 p/1 by/15/09/2021`

* Then the addJob command is complete and the user sees the following screen:

    <img src="images/ui-addJob-success.png" width="600px">

### Listing all jobs: `listJob`

Shows a list of all repair jobs in the CRM.

Format: `listJob`

### Deleting a job: `deleteJob`

Delete the specified repair job from the CRM

Format: `deleteJob INDEX`

* Deletes the repair job at the specified `INDEX`
* `INDEX` refers to the index of the repair job as shown in the repair job listing
* `INDEX` must be a positive integer(1,2,3…)

### Adding a contact: `addContact`

Add a new contact info of a client into the CRM.

Format: `addContact n/CLIENT_NAME c/CONTACT_NUMBER e/EMAIL a/ADDRESS`

* Creates a new contact info of a client
* In the case there happen to be multiple clients with the same name, a list of client names will be shown for the user to select from.
* Contact number, Address, Email are optional, but must have one of them to make it realistic to get access to the client.

Examples:

* `addContact n/Frisk c/93487234 e/Frisk@gmail.com a/Laptop Factory Outlet Bugis Junction`
* `addContact n/Sans c/83921823 e/Sans@gmail.com a/Maxwell Chambers 32 Maxwell Rd`
  
    <img src="images/ui-add-contact.png" width="600px">

### Listing all contacts: `listContact`

Show a list of all contact info in the CRM.

Format:  `listContact` 

### Deleting a contact: `deleteContact`

Deletes the specified contact from the CRM

Format: `deleteContact 4`

* Deletes the contact at the specified `INDEX`
* `INDEX` refers to the index of the contact as shown in the contact listing
* `INDEX` must be a positive integer(1,2,3…)

### Adding a product: `addProduct`

Add a new product to the CRM.

Format: `addProduct n/NAME [t/TYPE] [m/MANUFACTURER] [d/DESCRIPTION]`
* Product name is a compulsory field. It must be non-empty.

Examples:

* `addProduct n/Asus DUAL-GTX1060-O6G t/GPU m/Asus`
* `addProduct n/Intel i5-10400F t/CPU m/Intel d/2.90GHz`

    <img src="images/ui-add-product.PNG" width="600px">

### Listing all products: `listProduct`

Shows a list of all products in the CRM.

Format: `listProduct`

### Deleting a product: `deleteProduct`

Deletes the specified product from the CRM

Format: `deleteProduct INDEX`

* Deletes the product at the specified INDEX.
* INDEX refers to the index of the product as shown in the product listing.
* INDEX must be a positive integer(1,2,3…).

### Send mail: `mail`

Constructs an email to send to a customer of a specified job.

Format: `mail j/JOB_INDEX t/TEMPLATE_INDEX`

* Constructs a new email with template content and contact details of the job at the specified `JOB_INDEX` and
  `TEMPLATE_INDEX`.
* `JOB_INDEX` refers to the index of the job shown in the repair job listing.
* `JOB_INDEX` must be a positive integer (1,2,3…).
* `TEMPLATE_INDEX` refers to the index of the template as shown in the template listing.
* `TEMPLATE_INDEX` must be a positive integer (1,2,3…).

Examples:

* `listJobs` and `listTemplates` followed by` mail j/2 t/2` constructs an email to the 2nd job’s customer with the 2nd
  email template

    <img src="images/ui-send-mail.jpg" width="600px">

### Adding mail template: `addTemplate`


Adds a new email template to the CRM.

Format: `addTemplate s/SUBJECT b/BODY`

Examples:

* `addTemplate s/Repair Completed b/Your product has been completely repaired.`
* `addTemplate s/Repair Issue b/Your product has faced an issue which requires your attention.`

    <img src="images/ui-add-email-template.jpg" width="600px">

### Listing all templates: `listTemplate`

Shows a list of all templates in the CRM.

Format: `listTemplates`

### Deleting mail template: `deleteTemplate`

Deletes the specified template from the CRM.

Format: `deleteTemplate INDEX`

Deletes the template at the specified `INDEX`
* `INDEX` refers to the index of the template as shown in the template listing.
* `INDEX` must be a positive integer(1,2,3…).

Examples:
* listTemplate followed by deleteTemplate 2 deletes the 2nd email template in the CRM.

### Retrieve previous command: `history`

Retrieve the previously entered command

Format: `Press Up arrow key`/ `history`

* `Press Up arrow key` on the keyboard to gain the most recent command in the CRM.
* `history` will list all history commands in the CRM

Examples:
* `Press up arrow`:

```
addProduct n/Asus DUAL-GTX1060-O6G t/GPU m/Asus
```

* `history`: 

```
addProduct n/Asus DUAL-GTX1060-O6G t/GPU m/Asus
addTemplate s/Repair Issue b/Your product has faced an issue listTemplates
```


### Viewing user guide: `help`

Shows a message explaining how to access the help page.

Format: `help`

### Exiting the program : `exit`

End MyCRM and exit the programme.

Format: `exit`

### Loading JSON data: `[coming in v1.3]`

_Details coming soon ..._

### Purging JSON data: `[coming in v1.3]`

_Details coming soon ..._

### Pinning Jobs `[coming in v1.3]`

_Details coming soon ..._

### Customising User Interfaces `[coming in v1.3]`

_Details coming soon ..._

### Hiding Contacts `[coming in v1.3]`

_Details coming soon ..._

### Hiding Jobs `[coming in v1.3]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that
contains the data of your previous MyCRM home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action              | Format, Examples
--------------------|------------------
**Add Job**         | `addJob d/DESCRIPTION c/CONTACT_INDEX p/PRODUCT_INDEX by/DELIVERY_DATE` <br>e.g., `addJob d/CPU replacement needed c/1 p/1 by/15/09/2021`
**List Job**        | `listJob`
**Delete Job**      | `deleteJob INDEX` <br>e.g., `deleteJob 2`
**Add Contact**     | `addContact n/CLIENT_NAME c/CONTACT_NUMBER e/EMAIL a/ADDRESS` <br>e.g., `addContact n/Frisk c/93487234 e/Frisk@gmail.com a/Laptop Factory Outlet Bugis Junction`
**List Contact**    | `listContact`
**Delete Contact**  | `deleteContact INDEX` <br>e.g., `deleteContact 4`
**Add Product**     | `addProduct n/NAME [t/TYPE] [m/MANUFACTURER] [d/DESCRIPTION]`<br>e.g., `addProduct n/Asus DUAL-GTX1060-O6G t/GPU m/Asus`
**List Product**    | `listProduct`
**Delete Product**  | `deleteProduct INDEX`<br>e.g., `deleteProduct 4`
**Mail**            | `mail JOB_INDEX TEMPLATE_INDEX`<br>e.g., `mail 3 1`
**Add Template**    | `addTemplate s/SUBJECT b/BODY`<br>e.g., `addTemplate s/Repair In Progress b/Your product is current;y being repaired`
**List Templates**  | `listTemplates`
**Delete Template** | `deleteTemplate INDEX`<br>e.g., `deleteTemplate 4`
**Retrieve Previous Command** | `history`, `Press Up arrow key`
**Exit**            | `exit`

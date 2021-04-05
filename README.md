# Restaurant Pos
Point of Sale System for Famous Burger Stand
(Due July 8th)
Background: A famous burger stand has been in
business for over 50 years. They have been running
their business using paper and pen (very old
fashion). Their burgers are good, but they are not
very efficient. Your job is to automate their
operations. You will design and implement a full Point
of Sale system that allows them to manage all their
orders and generate reports so that they can
determine their profit and loss reports in real-time.
The system will manage inventory (for costs), income
and expenses, and all orders placed by customers.
The following identifies the key functionality of the
system:
MENU:
* Each item on the menu has a price and cost
associated with it.
* Your menu should contain a minimum of 5 different
items (hamburger, cheeseburger, double, fries, drink,
etc)
* Each menu item will require certain ingredients
(tomatoes, lettuce, etc). The ingredients are
managed as INVENTORY items.
INVENTORY:
* For each inventory item (e.g. Tomatoes), you
should track the number of units on hand, the cost to
purchase each unit, and any other data you feel is
relevant.
* You need to track the quantity on hand of each
inventory item.
INVENTORY REPORTS AND ORDERS:
* The burger stand owner can run a report at any
time to see how much of each inventory item is on
hand in real-time
* The burger stand owner can also place an order for
more inventory items. When this happens, the
amount paid for these inventory items should be
recorded.
PLACING CUSTOMER ORDERS:
* When a customer places an order, you can only
fulfill it if there is enough inventory to actually prepare
the item being ordered. Each customer will also have
an order number that is unique.
* Present a receipt to the customer. This should be
able to be output to a variety of sources (e.g. Console
or file, etc.).
REPORTS:
* Income (Customer Orders) Report
---give a date range, present all the orders placed
and the revenue collected.
---separately present the expenses (cost of inventory)
---The net income should be displayed (revenue less
total expenses)
* Current Inventory (already mentioned above)
* Orders by Product - presents a report of how many
of each item was ordered between a particular date
range
PERSISTENCE:
- This application should persist data so that when
the app is shut down, the current state of all data is
saved. So when you launch the app the next
morning, all the previous orders/inventory/etc are
loaded into memory.
DELIVERABLES:
-Full UML Class Diagram
-Full Source Code
-Java Docs
-The application should be deployed as a jar file
-Live presentation with me showing how the app was
designed and demonstrate that it is working

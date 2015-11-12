#homework-lv

##Goal
- Create a simple micro-lending rest api app similar to one of our existing products.

##Business requirements
- Applying for loan through the api - passing term and amount.
- Loan application risk analysis performed if:
  - the attempt to take loan is made after 00:00 with max possible amount.
  - reached max applications (e.g. 3) per day from a single IP.
- Loan can be extended, interest factor per week is 1.5.
- User can view their loans, including extensions.

##Technical requirements
- Backend in Java, XML-less Spring, Hibernate.
- Code quality (both production and test)
- How simple it is to run the application (embedded DB/embedded container)
- Use of spring-boot provided in this template is not obligatory, you are free to choose any other framework.

##What gets evaluated
- Requirements are met
- Code quality (both production and test)
- How simple it is to run the application (embedded DB/embedded container)
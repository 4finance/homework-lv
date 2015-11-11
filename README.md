#homework-lv

##Goal
- Create a simple micro-lending rest api app similar to one of our existing products.

##Business requirements
- Applying for loan through the api - passing term and amount.
- Loan application risk analysis is performed:
  - the attempt to take loan is made after 00:00 with max possible amount.
  - reached max applications (e.g. 3) per day from a single IP.
- Loan can be extended, for one week interest factor is 1.5.
- User can view his loans, including extensions.

##Technical requirements
- Backend in Java, XML-less Spring, Hibernate.
- Code is production-ready, covered with tests.
- Ability to run application from the command line.

##What gets evaluated
- Requirements
- Code quality (both production and test)
- How simple it is to run the application (embedded DB/embedded container)

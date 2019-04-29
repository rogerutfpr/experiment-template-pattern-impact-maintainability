# DAO Classes used in the experiment

## sireata-control
This folder contains the Maven project to the original 'sireata' DAO and Entity classes. Although we kept original DAO and Entity classes unchanged, we utterly changed the project to mitigating threats. For instance, the original project requires running a full database, which could complicate experimental setup. 

Summary of changes we performed:
- Reduced the project to three packages necessary for the experimental setup: dao, model and util;
- Included in the project only the two classes used in the experiment, along with their dependencies: AnexoDAO and PautaDAO;
- Configured an in-memory database to enable tests;
- Updated ConnectionDAO class to use our in-memory database and to create only tables required for the experiment;
- Created tests for entities and DAOs, including tests for tasks that should be performed in the experiment;
- Provided a main class with a main method to help students unfamiliar with testing.

## sireata-experiment
This folder contains the reengineered Maven project that uses the Template DP. Implementing the Template DP led to several changes in the sireata-control project although tests were kept unchanged in order to preserve compatibility - an important requirement in any code modification.

Summary of changes we performed:
- Created a superclass dao.TemplateDAO, which consists of original DAO methods replaced with Generics for enabling type replacement without casting, and  abstract template methods that should be replaced by concrete implementations;
- Changed original methods to be final and to use template methods when convenient;
- Changed dao.AnexoDAO and dao.PautaDAO classes to inherit from dao.TemplateDAO;
- Implemented abstract template methods in concrete classes dao.AnexoDAO and dao.PautaDAO by using original code from sireata-control project.


# Tasks

1. Update the project to pass the selected market object from the MarketListFragment to the MarketDetailScreen (Compose).
2. Implement the MVVM (Model-View-ViewModel) architecture in the MarketDetailScreen.
3. Populate the following fields in the MarketDetailScreen from the selected market object using the Text component:
      Epic
      Current Price
      Current Change
      Current Change Percentage
4. Use proper state composables to display the MarketDetailScreen effectively.
5. Ensure that any business logic in the ViewModel is covered by unit tests.
6. After completing the first task, what would you choose to do next? In this task we would like to see which would be your next priority and why.
   * I would try and do some refactoring to avoid using the data layer object in the ui layer.    
   * I would also add some dependency injection, which I ended up doing using Hilt. One of the reasons for this was so that I could properly test the viewModels.
7. If you had more time what would you improve?
      * I'd further improve the MVVM by adding clean arch. This would introduce a clear separation of concerns across the 
      different layers. We'd have 3 layers, namely, ui, domain, and data. 
      This would also allow us to avoid using the data layer objects in the ui layer.
      * I'd introduce mappers to map objects from one layer to the other
      * I'd add some UI tests
8. What would you do differently if you were to start from scratch?
      * I'd plan the architecture and the modularization strategy
      * I'd make sure the logic is implemented in a way that is testable and maintainable
      * I'd use compose for the MarketList screen, rather than using a fragment. 
      * I'd set up dependency injection


   




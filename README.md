# Tala Assessment

## Objective
The goal of this assignment is to build a simple single-screen app that shows differently for different data

## API Gateway
Mock API data.

## Structure
###### Application have two modules; app and network
App Module contains following:
- TalaApp.kt (application class)
- MainActivity.kt (only activity)
- FlowUseCase.kt
- Data Adapter
- Managers package
- Home feature package
- All Ui components

Network Module contains following:
- OkHttpClientProvider
- RetrofitProvider
- Helper classes for network stuff

###### Home module follows clean architecture with packages:
- data (data related stuff)
- domain (business logic)
- presentation (ui related stuff)

## Data Layer
- Service
- Response POJOs
- Ui Models
- Repository

## Presentation Layer
- Bindings
- Fragment
- Adapter + ViewHolder

## Domain Layer
- UseCase
- ViewModel

## DI
Hilt is used for dependency injection, following classes are used for DI related stuff.
- HomeModule
- ManagersModule
- NetworkModule

## Testing
This case study includes unit-tests for use-case, repository, viewModel
Frameworks used for testing
- Mockito
- JUnit

## Requirement
Given the array of data in testData.json, show a vertical scrolling list of cards for each item,
matching the provided comps (screen#.png) based on the data.
Users should be able to logically interact with each card, though what the app does when the user interacts with them is up to you.
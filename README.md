# Wayne Dyer Quotes API

## Introduction
The Wayne Dyer Quotes API is a RESTful web service that provides a collection of inspirational quotes by Wayne Dyer. This API allows users to retrieve quotes programmatically, making it suitable for integration into various applications, websites, or personal projects.

## Features
- **Get all quotes**: Retrieve a list of all Wayne Dyer quotes available in the API.
- **Filter by ID**: Retrieve a specific quote by its unique identifier.
- **Random quote**: Get a random quote from the collection.
- **Search by keyword**: Search for quotes containing specific keywords.
- **Pagination**: Implement pagination to limit the number of quotes returned per request.

## API Documentation
Please refer to the https://documenter.getpostman.com/view/2722958/2s93sjTTct for detailed information on how to use the Wayne Dyer Quotes API. The documentation provides examples and usage instructions for each available endpoint, including request/response formats and parameters.

## Technologies Used
- **Java**: The core programming language used for building the API.
- **Spring Boot**: A powerful framework for building Java applications, providing features such as routing, request handling, and dependency injection.
- **Maven**: A build and dependency management tool used for project configuration and packaging.
- **OpenCSV**: A library for reading and parsing CSV files, used to retrieve quotes from the provided CSV data source.

## Setup and Deployment
To set up the Wayne Dyer Quotes API locally, follow these steps:

1. Clone the repository:

2. Install the required dependencies:

3. Configure the API:
- Set the CSV file path in the `application.properties` file.
- Update any other required configuration settings, such as the server port.

4. Run the API:

The API will be accessible at https://wayne-dyer-quotes-api-4c40c19e5047.herokuapp.com/api/quotes

For deployment on a hosting platform, such as Heroku, follow the platform-specific instructions to deploy the application.

## Contributions
Contributions to the Wayne Dyer Quotes API are welcome! If you find any issues, have suggestions for improvements, or would like to add new features, please submit a pull request.

## License
The Wayne Dyer Quotes API is released under the [MIT License](link-to-license-file). Feel free to use, modify, and distribute the API according to the terms of the license.

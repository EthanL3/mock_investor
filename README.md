# Mock Investor App  
MockInvestor is a simple Android application that allows users to simulate buying and selling stocks in a virtual trading environment.

## Features

- View and manage a portfolio of stocks.
- Simulate buying and selling stocks.
- Real-time stock price updates.
- Save and load portfolio data.

## Setting up API:
- Create a config.properties file with a valid API key from https://www.alphavantage.co/support/#api-key (free)
- The config.properties filepath should be stock_app/app/main/src/assets/config.properties
- The sytax of the file is:
```txt
ALPHA_VANTAGE_API_KEY=YourAPIKeyHere
```

## Getting Started  
- To run the app on an android device, simply download the apk file located in the root directory of this repo (MockInvestor.apk)
- To run the app on an emulator, you will need to download android studio and follow the instructions below (Editing App)
- The API Key must be set up before running the app

## Editing App:
Prerequsities:  
- Download java, the latest version can be found at: https://www.oracle.com/java/technologies/downloads/
- Download android studio: https://developer.android.com/studio
1. Clone the repository:
```bash
git clone https://github.com/your-username/MockInvestor.git
```  
2. Create a local.properties file in the root directory: stock_app/local.properties should be the file path   
3. Copy and paste the following line into local.properties and then save the file: 
```txt
sdk.dir=C\:\\Users\\YourWindowsUserNameHere\\AppData\\Local\\Android\\Sdk
```
- Replace YourWindowsUserNameHere with your windows username. The default installation path of Sdk is above, if your installation path is any different you will have to change that.  
4. Build the gradle files (a 'sync files' should pop up at the top of the screen')  
5. Run the app using the run button 
6. If necessary, change the compile sdk version from 33 to 34 in the build.gradle.kts file
```kts
android {
    namespace = "com.example.mockinvestor"
    compileSdk = 34
```

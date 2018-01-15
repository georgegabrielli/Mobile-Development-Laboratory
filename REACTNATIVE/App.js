/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */
import React from 'react';
import StackNavigator from "react-navigation/lib-rn/navigators/StackNavigator";
import CarList from "./app/CarList";
import {CarEdit} from "./app/CarEdit";
import firebase from "firebase/index";
import {LoginScreen} from "./app/Login";
import MainScreen from "./app/MainScreen";
import Register from "./app/Register";

const MyNavigator = StackNavigator({

    Home:
        {
            screen: MainScreen,
            navigationOptions: {
                headerTitle: "Cars",
                // headerRight:<Button title = "+"/>
            }
        },

    CarList: {
        screen: CarList,
        navigationOptions: {headerTitle: "Your Cars"}
    },

    EditCar:
        {
            screen: CarEdit,
            navigationOptions: {headerTitle: "Add/Edit car"}
        },
    Login:
        {
            screen: LoginScreen,
            navigationOptions: {headerTitle: "Login"}
        },

    Register:
        {
            screen: Register,
            navigationOptions: {headerTitle: "Register"}
        }
});


export default class App extends React.Component {

    componentWillMount() {
        const config = {
            apiKey: "AIzaSyCBG5Sslsl87NTzCGGiX_1WnI-yBtpwcvs",
            authDomain: "gobicarstats.firebaseapp.com",
            databaseURL: "https://gobicarstats.firebaseio.com",
            projectId: "gobicarstats",
            storageBucket: "gobicarstats.appspot.com",
            messagingSenderId: "553027077856"
        };
        firebase.initializeApp(config);
        firebase.auth().signOut();
    }

    render() {
        return (
            <MyNavigator/>
        );
    }


}


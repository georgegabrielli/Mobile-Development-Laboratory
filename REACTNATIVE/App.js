/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React from 'react';
import StackNavigator from "react-navigation/lib-rn/navigators/StackNavigator";
import CarList from "./app/CarList";
import {CarEdit} from "./app/CarEdit";

const MyNavigator = StackNavigator({

    Home:
        {
            screen : CarList,
            navigationOptions : {headerTitle: "Your cars"}
        },

        EditCar:
            {
                screen : CarEdit,
                navigationOptions:{headerTitle: "Edit car"}
            }
});


export default class App extends React.Component{
    render() {
        return (
            <MyNavigator/>
        );
    }
}


import React from 'react';
import {View, Button, Text} from "react-native";

export default class MainScreen extends React.Component {

    _onPress1() {
        this.props.navigation.navigate("Register");
    }

    _onPress4() {
        this.props.navigation.navigate("Login");
    }

    render() {
        return ( //we cand return just one element
            <View>
                <Text>Cars</Text>
                    <View>
                        <Button onPress={() => this._onPress1()} title="Register"/>
                    </View>
                    <View>
                        <Button onPress={() => this._onPress4()} title="Login"/>
                    </View>
            </View>
        );
    }
};
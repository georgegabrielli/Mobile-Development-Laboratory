import React from "react";
import {Button, Text, TextInput, View} from "react-native";
import firebase from "firebase/index";

export class LoginScreen extends React.Component {
    constructor() {
        super();
        this.email = 'georgegabrielli@gmail.com';
        this.password = 'gobibrand';
    }

    render() {
        const {navigate} = this.props.navigation;

        return (
            <View>
                <Text>LOGIN</Text>
                <TextInput
                    style={{width: "80%", borderWidth: 1, backgroundColor: 'white'}}
                    onChangeText={(email) => this.email = email}
                    placeholder={"Email"}
                />
                <TextInput
                    style={{width: "80%", borderWidth: 1, backgroundColor: 'white'}}
                    onChangeText={(password) => this.password = password}
                    secureTextEntry={true}
                    placeholder={"Password"}
                />
                <Button
                    title="Sign in"
                    onPress={() => {
                        firebase.auth().signInWithEmailAndPassword(this.email, this.password)
                            .then(function () {
                                alert("Welcome " + firebase.auth().currentUser.email + "!");
                                    navigate("CarList");
                            }).catch(function (error) {
                            alert(error.code);
                            alert(error.message);
                        });
                    }}
                />
            </View>
        )}

}
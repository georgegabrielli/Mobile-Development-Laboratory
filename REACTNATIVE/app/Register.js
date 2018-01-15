import React from 'react';
import {Button, Text, TextInput, View} from "react-native";

export default class Register extends React.Component{
    constructor(props){
        super(props);
            this.email = "";
            this.password = "";
    }
    _onPress(){
        // Linking.openURL("mailto:"+this.state.email+"?subject=LifeSum&body=Hello "+this.state.name+" welcome to LifeSum");
        firebase.auth().createUserWithEmailAndPassword(this.email, this.password).catch(function(error){
            console.log(error.message);
            alert(error.message);
        });
        this.props.navigation.goBack();
    }
    render(){
        return( //we cand return just one element
            <View>
                <Text>Register</Text>
                <View>
                    <TextInput
                               placeholder="Email..."
                               placeholderTextColor="white"
                               onChangeText={(email)=>this.email = email}
                               value={this.email}/>

                    <TextInput
                               secureTextEntry={true}
                               placeholder="Password..."
                               placeholderTextColor="white"
                               onChangeText={(password)=>this.password = password}
                               value={this.password}/>
                </View>
                    <Button onPress={()=>this._onPress()} title="Submit"/>
            </View>
        );
    }
};
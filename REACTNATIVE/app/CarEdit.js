import * as React from "react";
import {AsyncStorage, TextInput, View, StyleSheet, Text, Button, Picker} from "react-native";
import firebase from 'firebase/index';

export class CarEdit extends React.Component {
    constructor() {
        super();
        this.make = '';
        this.model = '';
        this.cubicCapaity = '';
        this.year = '';
    }

    render() {
        const {goBack} = this.props.navigation;
        const {item} = this.props.navigation.state.params;
        const {refresh} = this.props.navigation.state.params;

        this.setCarDetails(item);

        return (
            <View style={styles.container}>
                <Text>Chassis Code</Text>
                <TextInput
                    style={{width: "80%", borderWidth: 1, backgroundColor: 'white'}}
                    onChangeText={(text) => this.chassisCode = text}
                    placeholder={"Chassis Code"}
                    editable={this.isNew}>
                    {this.chassisCode}
                </TextInput>

                <Text>Make</Text>
                <Picker
                    style={{width: "80%", borderWidth: 1, backgroundColor: 'white'}}
                    selectedValue={this.makeSelection}
                    onValueChange={(itemValue) => {
                        console.warn("Value changed: " + itemValue);
                        this.makeSelection = itemValue;
                    }}>
                    <Picker.Item label="BMW" value="BMW"/>
                    <Picker.Item label="Chevrolet" value="Chevrolet"/>
                    <Picker.Item label="Dacia" value="Dacia"/>
                </Picker>

                <Text>Model</Text>
                <TextInput
                    style={{width: "80%", borderWidth: 1, backgroundColor: 'white'}}
                    onChangeText={(text) => this.model = text}
                    placeholder={"Model"}>
                    {this.model}
                </TextInput>

                <Text>Cubic Capacity</Text>
                <TextInput
                    style={{width: "80%", borderWidth: 1, backgroundColor: 'white'}}
                    onChangeText={(text) => this.cubicCapacity = text}
                    placeholder={"Cubic Capacity"}>
                    {this.cubicCapaity}
                </TextInput>

                <Text>Year</Text>
                <TextInput
                    style={{width: "80%", borderWidth: 1, backgroundColor: 'white'}}
                    onChangeText={(text) => this.year = text}
                    placeholder={"Year"}>
                    {this.year}
                </TextInput>

                <Button title="Save" onPress={() => {
                    if(this.isNew){
                    let id = firebase.database().ref().child('cars').push().key;
                    firebase.database().ref('cars').child(id).update({
                        id: id,
                       chassisCode: this.chassisCode,
                        make: this.make,
                        model: this.model,
                        cubicCapacity: this.cubicCapacity,
                        year: this.year
                    });}else{
                        firebase.database().ref('cars').child(this.id).update({
                            id: this.id,
                            chassisCode: this.chassisCode,
                            make: this.make,
                            model: this.model,
                            cubicCapacity: this.cubicCapacity,
                            year: this.year
                        });
                    }

                    // Communications.email(["gabrielli.george@gmail.com", "gabrielli.george@gmail.com"], null, null, "CAR UPDATED", item.getchassisCode());
                    refresh();
                    goBack();
                }}>
                </Button>
            </View>
        )
    }

    setCarDetails(item) {
        this.id = item.getID();
        this.model = item.getmodel();
        this.cubicCapacity = item.getcubicCapacity();
        this.year = item.getyear();
        this.chassisCode = item.getchassisCode();
        this.isNew = item.getisNew();
        this.makeSelection = item.getmake();
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        alignItems: 'center',
        backgroundColor: "#ffffff",
    },

});
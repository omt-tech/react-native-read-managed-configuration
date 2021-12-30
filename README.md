# react-native-read-managed-configuration

## Note this only works with android. this will not work with ios.

## Getting started

`$ npm install git://github.com/omt-tech/react-native-read-managed-configuration.git#master --save`

### Link required after installation if React Native < 0.60

`$ react-native link react-native-read-managed-configuration`

## Usage
```javascript
import ReadManagedConfiguration from 'react-native-read-managed-configuration';

const data = await ReadManagedConfiguration.getAllRestrictions();
// data will look like {keyOfconfig1: valueOfConfig1, keyOfConfig2: valueOfConfig2, ...}
// do stuff with data
// data = {managed_config_key1: managed_config_data1, managed_config_key2: managed_config_data2, ...}
// this will not work with .then, .catch, .finally callbacks. only with async/await due to restrictions with react native native modules
```

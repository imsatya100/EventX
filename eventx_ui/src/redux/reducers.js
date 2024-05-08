// reducers.js
import { combineReducers } from 'redux';

// Initial state for storing object
const initialObjectState = {
  storedObject: null
};

// Reducer for storing object
const objectReducer = (state = initialObjectState, action) => {
  switch (action.type) {
    case 'STORE_OBJECT':
      return {
        ...state,
        storedObject: action.payload
      };
    default:
      return state;
  }
};

// Initial state for forwarding message
const initialMessageState = {
  forwardedMessage: ''
};

// Reducer for forwarding message
const messageReducer = (state = initialMessageState, action) => {
  switch (action.type) {
    case 'FORWARD_MESSAGE':
      return {
        ...state,
        forwardedMessage: action.payload
      };
    default:
      return state;
  }
};

// Combine reducers
const rootReducer = combineReducers({
  object: objectReducer,
  message: messageReducer
});

export default rootReducer;
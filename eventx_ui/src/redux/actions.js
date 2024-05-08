// actions.js
export const FORWARD_MESSAGE = 'FORWARD_MESSAGE';

export const forwardMessage = (message) => ({
  type: FORWARD_MESSAGE,
  payload: message
});

export const STORE_OBJECT = 'STORE_OBJECT';

export const storeObject = (object) => ({
  type: STORE_OBJECT,
  payload: object
});
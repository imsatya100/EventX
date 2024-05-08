import React, { useEffect } from 'react';
import { Modal } from 'react-bootstrap';
const ModalDialougePopUP = ({ show, onClose, title, bodyContent }) => {

  useEffect(() => {
      if (show) {
      }
  }, [show]);

return (
  <>
    <Modal show={show} onHide={onClose} backdrop="static" keyboard={false}>
      <Modal.Header closeButton>
        <Modal.Title>{title}</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        {bodyContent}
      </Modal.Body>
    </Modal>
  </>
 );
};
  
  export default ModalDialougePopUP;
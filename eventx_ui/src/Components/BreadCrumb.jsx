import React from 'react';
import { MDBBreadcrumb, MDBBreadcrumbItem } from 'mdb-react-ui-kit';

const BreadCrumb = () => {
  return (
    <MDBBreadcrumb>
      <MDBBreadcrumbItem>
        <a href="/" className="active">Recipients</a>
      </MDBBreadcrumbItem>
      <MDBBreadcrumbItem>
        <a href="/" className="hidden-xs breadcrumb_0">Requests</a>
      </MDBBreadcrumbItem>
      <MDBBreadcrumbItem>
        <a href="/">Home</a>
      </MDBBreadcrumbItem>
    </MDBBreadcrumb>
  );
};
export default BreadCrumb;

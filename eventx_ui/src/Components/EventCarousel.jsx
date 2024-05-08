import React, { useState, useEffect } from 'react';
import Carousel from 'react-bootstrap/Carousel';

const EventCarousel = () =>{
  const [events, setEvents] = useState([]);
  const [selectedEvent, setSelectedEvent] = useState(null);
  const handleSlideClick = (event) => {
    const eventId = parseInt(event.target.getAttribute('data-event-id'), 10);
    const eventDetails = events.find(event => event.id === eventId);
    setSelectedEvent(eventDetails);
  };
  

  
  useEffect(() => {
    const dummyEvents = [
        {
          id: 1,
          title: 'Event 1',
          description: 'Description for Event 1',
          details:'Join us for a fantastic weekend of live music in the heart of the countryside. Get ready to dance to the beats of top artists and bands from around the world!',
          imageUrl: 'images/Event1.jpg',
        },
        {
          id: 2,
          title: 'Event 2',
          description: 'Description for Event 2',
          details:'Join us for a fantastic weekend of live music in the heart of the countryside. Get ready to dance to the beats of top artists and bands from around the world!',
          imageUrl: 'images/Event2.jpg',
        },
        {
          id: 3,
          title: 'Event 3',
          description: 'Description for Event 3',
          details:'Join us for a fantastic weekend of live music in the heart of the countryside. Get ready to dance to the beats of top artists and bands from around the world!',
          imageUrl: 'images/Event3.jpg',
        },
      ];
    
    setEvents(dummyEvents);
    // Fetch events from the API when the component mounts
    /* fetchEvents()
      .then(data => setEvents(data))
      .catch(error => console.error('Error fetching events:', error)); */
  }, []); // Empty dependency array means this effect only runs once after the component is mounted

  return (
    <Carousel>
      {events.map(event => (
        <Carousel.Item key={event.id}>
          <img
            className="d-block w-100"
            src={event.imageUrl}
            alt={event.title}
            data-event-id={event.id}
            onClick={handleSlideClick}
          />
          <Carousel.Caption>
            <h3>{event.title}</h3>
            <p>{event.description}</p>
            {event.details}
          </Carousel.Caption>
        </Carousel.Item>
      ))}
    </Carousel>
  );
}

export default EventCarousel;

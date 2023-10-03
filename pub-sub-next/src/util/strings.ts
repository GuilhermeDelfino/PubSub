const strings = {
  validation: {
    REQUIRED: "This field must be not empty",
    MIN_SIZE: `This field must be greater than {min} characters.`,
    MAX_SIZE: "This field must be less or equal than {max} characters.",
    NOT_CONTAINS_NUMBER: "This field must be not contains numbers",
    DATE_REQUIRED: `This date must be not empty`,
    DATE_MIN_INVALID: "This date must be greater than {date}.",
    DATE_MAX_INVALID: "This date must be less or equal than {date}.",
  },
  createSubscriber: {
    title: "Subscribe for new events",
    description:
      "Enter your name and email, then you will receive new events when it will be created!",
    response: {
      success: "Subscriber have been subscribed!",
    },
    buttons: {
      createButton: {
        text: "Subscribe!",
      },
    },
    inputs: {
      name: {
        placeholder: "Example",
        title:
          "Type a value between 3 and 50 characters and not contains numbers",
        text: "Name: ",
        errors: {
          IS_EMPTY: "Name must be not empty",
          INVALID:
            "Name must have between 3 and 50 characters and not contains numbers",
        },
      },
      email: {
        placeholder: "example@email.com",
        title: "Type a valid email",
        text: "Email: ",
        errors: {
          IS_EMPTY: "Email must not empty",
          INVALID: "Email must be valid",
        },
      },
    },
  },
  publishNewEvent: {
    title: "Publish your new Event in our plataform",
    description:
      "Enter event fields, then you will create your event. Later all of our subscribers will be notificate of your event",
    response: {
      success: "Event has been created with ID: {id}!",
      error: "Not be able to publish your event now",
    },
    buttons: {
      createButton: {
        text: "Publish!",
      },
    },
    inputs: {
      title: {
        placeholder: "Your event title",
        title:
          "Type a value between 3 and 50 characters and not contains numbers",
        text: "Title: ",
      },
      location: {
        placeholder: "Localization",
        title:
          "Type a value between 3 and 60 characters and not contains numbers",
        text: "Location: ",
      },
      date: {
        placeholder: "00/00/00 - 00:00:00",
        title: "Type a date and time later from today",
        text: "Date: ",
        errors: {
          IS_EMPTY: "Date must be not empty",
          INVALID: "Date invalid",
        },
      },
      description: {
        placeholder: "Details of your event",
        title:
          "Type a value between 3 and 255 characters and not contains numbers",
        text: "Description: ",
      },
      authorName: {
        placeholder: "Author's Name",
        title: "Enter between 3 and 50 characters",
        text: "Author: ",
      },
    },
  },

  header: {
    HOME: ["Home", "/"],
    RECENT_EVENTS: ["Recent Events", "/events"],
    PUBLISH_EVENT: ["Create an event", "/events/publish"],
    SUBSCRIBE: ["Subscribe now", "/subscribe"],
  },
};

export default strings;

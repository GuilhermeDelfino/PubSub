export const MS_SUBSCRIBERS_QUERIES = {
  FIND_ALL: `
  query FindAllQuery{
    findAll {
      id
      name
      email
    }
  }`,
  CREATE_SUBSCRIBER: `
    mutation CreateSubscriberMutation($subscriber: CreateSubscriberInput) {
    createSubscriber(input: $subscriber) {
      id
      name
      email
    }
  }
  `,
  FIND_BY_ID: `
  query FindByIdQuery($id: ID) {
    findById(id: $id) {
      id
      email
      name
    }
  }`,
};

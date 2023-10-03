export const QUERY_FIND_ALL_EVENTS = `
    query {
        findAllEvents {
        author
        date
        description
        id
        location
        title
        }
    }
`;

export const QUERY_FIND_ONE_EVENT = `
        query ($id: String){
            findOneEvent(id: $id) {
                author
                date
                description
                id
                location
                title
            }
        }
`;

export const MUTATION_CREATE_EVENT = `
    mutation ($input: CreateEventInput){
        createEvent(data: $input){
            id
            author
        }
    }
`;

export const MUTATION_CREATE_SUBSCRIBER = `
        mutation ($subscriber: CreateSubscriberInput) {
            createSubscriber(data: $subscriber) {
                id
                name
                email
            }
        }
    `;

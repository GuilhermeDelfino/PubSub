export const MUTATION_CREATE_SUBSCRIBER = `
        mutation ($subscriber: CreateSubscriberInput) {
            createSubscriber(input: $subscriber) {
                id
                name
                email
            }
        }
    `;

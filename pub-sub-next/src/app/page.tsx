import { Button, Title } from "@/components/atoms";
import { Container } from "@/components/atoms/Container";
import { Paragraph } from "@/components/atoms/Paragraph";
import { Row } from "@/components/atoms/Row";

export default function Home() {
  return (
    <main>
      <Container size="sm">
        <Title>Publish and Subscribe in Events Now</Title>
        <Paragraph>
          Here, you can publish new events for all of our subscribers
        </Paragraph>
        <Paragraph>
          Also you can subscribe for receive in your email box new events!
        </Paragraph>
        <Row>
          <Button variant="primary">Subscribe now</Button>
          <Button variant="secondary">Publish a new Event</Button>
        </Row>
      </Container>
    </main>
  );
}

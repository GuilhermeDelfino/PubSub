import { Module } from '@nestjs/common';
import { GraphQLModule } from '@nestjs/graphql';
import { ApolloDriver, ApolloDriverConfig } from '@nestjs/apollo';
import { SubscriberModule } from './subscribers/subscriber.module';
import { ConfigModule } from '@nestjs/config';
import { EventsModule } from './events/events.module';
@Module({
  imports: [
    GraphQLModule.forRoot<ApolloDriverConfig>({
      driver: ApolloDriver,
      autoSchemaFile: true,
      sortSchema: true,
      autoTransformHttpErrors: true,
      includeStacktraceInErrorResponses: false,
      status400ForVariableCoercionErrors: true,
    }),
    ConfigModule.forRoot(),
    SubscriberModule,
    EventsModule,
  ],
})
export class AppModule {}

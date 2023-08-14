# ATA Week 16 Activity
Follow the instructions on lms.kenzie.academy to complete this assignment.

# Gradle Commands
## In-Memory Caching - Orders
Run Unit Tests for the InMemoryCaching Orders activity

```bash
./gradlew inmemorycaching-orders-test
```

## In-Memory Caching - Kenzie Gaming
Set up and deploy tables for Kenzie Gaming
```bash
aws cloudformation create-stack --stack-name inmemorycaching-kenziegaming --template-body file://InMemoryCaching/KenzieGaming/dynamodb_tables.yaml
aws cloudformation wait stack-create-complete --stack-name inmemorycaching-kenziegaming
aws dynamodb batch-write-item --request-items file://InMemoryCaching/KenzieGaming/Groups_seeddata.json
aws dynamodb batch-write-item --request-items file://InMemoryCaching/KenzieGaming/GroupMemberships_seeddata.json
aws dynamodb batch-write-item --request-items file://InMemoryCaching/KenzieGaming/GroupMembershipAudits_seeddata.json
```

Run Unit Tests for the InMemoryCaching KenzieGaming activity

```bash
./gradlew inmemorycaching-kenziegaming-groupmembershipcaching-test
```

Run Phase 0 Integration Tests for the InMemoryCaching KenzieGaming activity

```bash
./gradlew inmemorycaching-kenziegaming-test-phase0
```

Run Phase 2 Integration Tests for the InMemoryCaching KenzieGaming activity

```bash
./gradlew inmemorycaching-kenziegaming-test-phase2
```

## Lambda Expressions - Price Manager
Run Unit Tests for the LambdaExpressions Price Manager activity
```
./gradlew lambdaexpressions-pricemanager-test
```

## Lambda Expressions - Number Generator
Run Unit Tests for the LambdaExpressions NumberGenerator activity
```
./gradlew lambdaexpressions-numbergenerator-test
```

## Lambda Expressions - Customer Manager
Run Unit Tests for the LambdaExpressions CustomerManager activity
```
./gradlew lambdaexpressions-customermanager-test
```

## Lambda Expressions - Shipping Helper
Run Unit Tests for the LambdaExpressions ShippingHelper activity
```
./gradlew lambdaexpressions-shippinghelper-test
```

## Group Work - IceCream

Run Unit Tests for each phase
```
./gradlew groupactivity-icecream-test-phase0
./gradlew groupactivity-icecream-test-phase1
./gradlew groupactivity-icecream-test-phase2
./gradlew groupactivity-icecream-test-phase3
```

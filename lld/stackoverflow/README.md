# Design Stack Overflow

### 1. Clarifying Requirements

1.1 Functional Requirements
   - Users can post questions and answers.
   - Users can vote on questions and answers (upvote/downvote). (Only one vote per post)
   - The Original Poster (OP) can accept one answer per question.
   - Users earn or lose reputation based on votes and their contributions.
   - Users can comment on questions and answers.
   - Questions can have multiple tags for categorization.
   - Users can search for questions using keywords and filter by tags.

1.2 Non-Functional
- Consistency
- Concurrency 
- Scalability

### 2. Identifying Core Entities
- User
- Question
- Answer
- Comment
- Vote
- Tag
- Reputation
- SearchService

### 3. Designing Classes and Relationships
3.1 Class Definitions
- Enums
  - VoteType: UPVOTE, DOWNVOTE
  - EventType: QUESTION_POSTED, ANSWER_POSTED, COMMENT_POSTED, VOTE_CAST
- Data Classes
  - User
    - userId: String
    - username: String
    - reputation: AtomicInteger
  - Tag
    - name: String

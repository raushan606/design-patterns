# Requirements:

1. Carrier deposits a package by specifying size (small, medium, large)
    - System assigns an available compartment of matching size
    - Opens compartment and returns access token, or error if no space
2. Upon successful deposit, an access token is generated and returned
    - One access token per package
3. User retrieves package by entering access token
    - System validates code and opens compartment
    - Throws specific error if code is invalid or expired
4. Access tokens expire after 7 days
    - Expired codes are rejected if used for pickup
    - Package remains in compartment until staff removes it
5. Staff can open all expired compartments to manually handle packages
    - System opens all compartments with expired tokens
    - Staff physically removes packages and returns them to sender
6. Invalid access tokens are rejected with clear error messages
    - Wrong code, already used, or expired - user gets specific feedback

Out of scope:

- How the package gets to the locker (delivery logistics)
- How the access token reaches the customer (SMS/email notification)
- Lockout after failed access token attempts
- UI/rendering layer
- Multiple locker stations
- Payment or pricing

## Class Design

The Amazon Locker system is designed to provide a secure and convenient way for customers to pick up their packages. The system consists of several
key classes that work together to manage lockers, packages, and customer interactions.

## Class Design

### 1. Locker

- **Attributes:**
    - compartments: Compartment[] - An array of compartments in the locker.
    - accessTokenMapping: Map<String, AccessTokens> - A mapping of access tokens to compartments.
- **Methods:**
    - depositPackage(size) -> String | error
    - pickup(string) -> void | error
    - openExpiredCompartments() -> void

### 2. AccessToken:

- **Attributes:**
    - tokenId: String - Unique identifier for the access token.
    - expirationTime: DateTime - The time when the token expires.
    - compartment: Compartment - The compartment associated with the token.
- **Methods:**
    - isValid() -> Boolean
    - getCompartment() -> Compartment
    - getTokenId() -> String

### 3. Compartment:

- **Attributes:**
    - size: Enum (SMALL, MEDIUM, LARGE) - The size of the compartment.
    - isOccupied: Boolean - Indicates if the compartment is occupied.
- **Methods:**
    - open() -> void
    - close() -> void
    - isOccupied() -> Boolean
    - markOccupied() -> void
    - markVacant() -> void
    - getSize() -> Enum

## Implementation

```python
class Locker:
    depositPackage(size):
    """
    Core Logic:
    1. Find comparment of the right size
    2. Open the comparment
    3. Mark it as occupied
    4. Generate access token
    5. Map access token to comparment
    6. Return access token
    
    Edge Cases:
    - No comparment of the right size available
    """
    
    comparment = getAvailableCompartment(size)
    if comparment is None:
        raise Error("No available compartment of the requested size.")
    comparment.open()
    comparment.markOccupied()
    accessToken = generateAccessToken(comparment)
    accessTokenMapping[accessToken.getTokenId()] = accessToken
    return accessToken.getTokenId()

    private getAvailableComparment(size):
        """
        1. Iterate through compartments
        2. Return first available compartment of matching size
        3. If none found, return None
        """
        for c in compartments:
            if c.getSize() == size and not c.isOccupied():
                return c
        return None
    
    pickup(accessTokenId):
        """
        Core Logic:
        1. Lookup access token
        2. get compartment from access token
        3. Open the compartment
        4. Mark it as vacant
        5. Remove access token from mapping
        
        Edge Cases:
        - Invalid access token
        - Expired access token
        """
        if accessTokenId.isEmpty():
            raise Error("Access token cannot be empty.")
        accessToken = accessTokenMapping.get(accessTokenId)
        if accessToken is None:
            raise Error("Invalid access token.")
        if not accessToken.isValid():
            raise Error("Access token has expired.")
        comparment = accessToken.getCompartment()
        comparment.open()
        comparment.markVacant()
        del accessTokenMapping[accessTokenId]
    

```
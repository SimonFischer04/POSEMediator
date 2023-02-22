# POSEMediator

Github(https://github.com/SimonFischer04/POSEMediator): This is only a clone of the main Repository.
Feel free to contribute or open an issue here: https://gitlab.fischerserver.eu/htlperg/posemediator

\<TODO\>

## planned:
- [ ] JSF UI:
    - [ ] credential management page
    - [X] mute state overlay
        - [X] mute state UI mit animation fia on / off
        - [X] load inital state from server
        - [X] toggle mute State in UI
    - [X] websocket
- [ ] Spring Backend:
    - [ ] discord
    - [X] http interface
        - [X] interface
    - [X] Mediator
    - [X] mockdiscord
    - [X] websocket
    - [X] spring 3!
    - [X] db mit credentials:
        - [X] db
- [X] lib thing... (some documentation)

## possible future improvement Ideas:
- [ ] dbus interface
- [ ] better Logging System
- [ ] ui: use constructor Inject (once told me field injection was not recommended, but now not anymore: maybe Jakarta EE 10 and switched back to 9 ???)
- [ ] Unit Tests
- [ ] db credential encryption
    - [ ] encryption
    - [ ] yubikey unlock: (©️ me 19.2.2023): currently NO! ->
        - WebAuth:
            - broken mit Spring Security 6 / Java 17 / Jakarta.y namespace...
            - a nd really sinn von programm, weil direkt am 'backend' (spring) host und nd im UI authentifizieren eigentlich
        - PIV:
            - "no" documentation, nur .NET SDK
            - javax.smartcardio.y a besonders mit Java17...
- [ ] http interface
    - [ ] esp trigger

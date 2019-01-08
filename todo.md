#Issues and bugs
- [X] *(B.1)*When changing value in slot after simulation is done the next simulation still uses old value from this slot
- [X] *(B.2)*Reopening Valuables window does not check for already chosen decos in source list allowing to add same deco multipe times
- [ ] *(B.3)*Scrolling through deco sets using buttons does not update ScrollPane bar position and causes focused set to go out of focus
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
#Implement
- [X] Button for backing up save
- [X] Integrate logic of BackupHandler with GUI
- [ ] Optional automatic adding of NextSet when game savefile was changed (when game saves)
- [ ] (*Later*) Automatic detection of Steam UserID by reading memory of steamapi.dll (Use JNA)
- [ ] Integrate Propsicl
- [ ] Add JSON parser for loading and saving Deco lists
- [ ] Decorate DecoRecord and DecoSlots
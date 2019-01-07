/* Examine the entries that were on the group vs. the entries that are there now. 
 * If the group has been modified, 'difference' of these sets will have > 0 members
 * XOR will give us any item that is not included in the other collection 
 * We could also implement this with two iterations of each collection to see if the 
 * other collection contains the entries from the current collection. 
 */
function showUpdateDepositButton() {
    var selectedEntries = vm.depositGroupUpdate.selected; 
    var existingEntries = vm.depositGroup.ledgerEntries;
    var difference = _.xorBy(selectedEntries, existingEntries, 'ledgerEntryID');
    
    return difference.length === 0 || selectedEntries.length === 0;
}

// Alternate implementation 
function showUpdateDepositButton() {

    var selectedEntries = vm.depositGroupUpdate.selected; 
    var existingEntries = vm.depositGroup.ledgerEntries;
    var difference = [];

    function contains(collection, other) {
        var result = true; 
        collection.forEach(function(entry) {
            if(!existingEntries.includes(entry)) {
                result = false; 
            }
        });
        return result;
    }

    return contains(selectedEntries, existingEntries) && contains(existingEntries, selectedEntries);
}
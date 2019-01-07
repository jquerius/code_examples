/// <summary>
/// Add or remove entries from a deposit group. Ensure that there are 
/// entries included on the group and that none of the entries are tied 
/// to a different deposit group. 
/// </summary>
/// <param name="deposit"></param>
/// <returns>Number of entries that are included in the deposit group.</returns>
public int UpdateDepositGroup(DepositGroup deposit)
{

    // ... 

    // Get the existing deposit group, make sure it already exists 
    var deposit = DepositGroupRepo.GetDepositByDepositGroupID(deposit.CustomerID, deposit.DepositGroupID);
    if(deposit == null)
    {
        // Throw exception 
    }

    // Set new amounts for deposit group 
    deposit.Amount = entries.Sum(e => e.Amount);

    // Save deposit info to database 
    DepositGroupRepo.Save(deposit);
    if (deposit.DepositGroupID <= 0)
    {
        // Throw exception 
    }

    // Get all existing ledger entries for this deposit group 
    var existingLedgerEntries = DepositGroupToLedgerEntriesService.GetByDepositGroupID(deposit.CustomerID, deposit.DepositGroupID);

    // Find all entries on this deposit request that have not been grouped yet.k
    // We will insert these ledger entries in the relational table. 
    var newLedgerEntries = entries
        .Where(
            newLedgerEntry =>
            !existingLedgerEntries
                .Select(existing => existing.LedgerEntryID)
                .Contains(newLedgerEntry.LedgerEntryID))
        .Select(newLedgerEntry => newLedgerEntry.LedgerEntryID)
        .ToList();

    // Find any newly-orphaned ledger entries that were on the deposit group previously, but were removed on this request 
    var removedLedgerEntries = existingLedgerEntries
        .Where(
            removed => 
                !entries.Select(le => le.LedgerEntryID)
                    .Contains(removed.LedgerEntryID))
        .Select(le => le.LedgerEntryID).ToList();
    
    try
    {
        // Save those new ledger entries!
        // Delete those old ledger entries!
    }

    catch(Exception ex)
    {
        // cleanup any entries that may have been added
        // log exception and return 
    }

    return deposit.LedgerEntryIds.Count;
}
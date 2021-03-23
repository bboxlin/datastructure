/*
 *  Microassignment: Probing Hash Table addElement and removeElement
 *
 *  IntegerHasher: Provides an object able to hash Integers
 * 
 *  Copyright:
 *   For academic use only under the Creative Commons
 *   Attribution-NonCommercial-NoDerivatives 4.0 International License
 *   http://creativecommons.org/licenses/by-nc-nd/4.0
 */


class IntegerHasher extends HasherBase<Integer>
{
    public int getHash(Integer key, int tableSize)
    {
        return key % tableSize;
    }
}
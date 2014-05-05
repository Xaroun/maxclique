package com.github.steveash.maxclique;

import com.carrotsearch.hppc.IntArrayDeque;
import com.carrotsearch.hppc.IntDeque;
import com.carrotsearch.hppc.IntOpenHashSet;
import com.carrotsearch.hppc.IntSet;

/**
 * Partial clique is adding one element to an existing partial clique with one special "null partial" representing
 * the primordial clique
 * @author Steve Ash
 */
public class PartialClique {


    public static final PartialClique nullPartial = new PartialClique(new IntOpenHashSet(), new IntArrayDeque(), -1);

    private final /* immutable */ IntSet members;
    private final IntDeque leftToCheck;
    private final double memberWeight;

    public PartialClique(IntSet members, IntDeque leftToCheck, double memberWeight) {
        this.members = members;
        this.leftToCheck = leftToCheck;
        this.memberWeight = memberWeight;
    }

    public boolean hasMoreToCheck() {
        return leftToCheck.size() > 0;
    }

    public int popNextToCheck() {
        return leftToCheck.removeFirst();
    }

    public IntSet getMembers() {
        return members;
    }

    public int size() {
        return members.size();
    }

    public IntSet copyOfMembersPlus(int appendThis) {
        IntSet newMembers = new IntOpenHashSet(this.members);
        newMembers.add(appendThis);
        return newMembers;
    }

    public double getMemberWeight() { return memberWeight; }

    public <T> Clique<T> convertToClique(Graph<T> g) {
        return new Clique<>(g.verticiesForIndexes(this.members), memberWeight);
    }
}

package com.howtographql.hackernews;

import java.util.List;

public class VoteRepository extends Repository<Vote> {
  public VoteRepository() {
    super(Vote.class);
  }

  public List<Vote> findByUserId(String userId) {
    return super.findItemsWhereAttributeEquals("userId", userId);
  }

  public List<Vote> findByLinkId(String linkId) {
    return super.findItemsWhereAttributeEquals("linkId", linkId);
  }

  public Vote saveVote(Vote vote) {
    vote.setId(IDUtil.generateID());
    mapper.save(vote);
    return vote;
  }
}
package largetest.dao;

import largetest.domain.Vote;

import java.util.Date;

public interface IVoteDAO extends IRootDAO<Vote> {
    Vote findVote(Date date, Long userId);
}
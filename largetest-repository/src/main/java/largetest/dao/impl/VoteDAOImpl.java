package largetest.dao.impl;

import largetest.dao.IVoteDAO;
import largetest.domain.Vote;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Repository
public class VoteDAOImpl extends RootDAOImpl<Vote>  implements IVoteDAO {
    private final static Logger logger = LoggerFactory.getLogger(VoteDAOImpl.class);

    public VoteDAOImpl() {
        super("largetest.domain.Vote", Vote.class);
    }

    @Override
    public Vote findVote(Date date, Long userId) {
        Query query = getSession().createQuery("from Vote where user.id =:userId and DATE(date) =:date");
        query.setLong("userId", userId);
        query.setDate("date", date);
        List<Vote> list = query.list();
        if(CollectionUtils.isEmpty(list)){
            return null;
        }else{
            logger.debug("More than 1 votes found. Must be only one. userId={},date={}", userId,date);
            return list.get(0);
        }
    }

}


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

import static com.tmhana.domain.QReviewInfo.reviewInfo;

@Repository
@RequiredArgsConstructor
public class ReviewInfoRepoImpl implements ReviewInfoRepoCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ReviewInfo> findByFilter(String storeName, Integer scoreRange) {

        var query = queryFactory
                .selectFrom(reviewInfo)
                .where(
                        storeName != null ? reviewInfo.context.contains(storeName) : null,
                        scoreRange != null ? reviewInfo.score.between(scoreRange * 10, scoreRange * 10 + 9) : null
                )
                .orderBy(reviewInfo.regidate.desc());

        return query.fetch();
    }
}
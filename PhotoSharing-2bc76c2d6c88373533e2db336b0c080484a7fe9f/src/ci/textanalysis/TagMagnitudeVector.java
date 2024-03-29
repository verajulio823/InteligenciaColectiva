package ci.textanalysis;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface TagMagnitudeVector {
    
    public List<TagMagnitude> getTagMagnitudes();

    public Map<Tag, TagMagnitude> getTagMagnitudeMap();

    public double dotProduct(TagMagnitudeVector o);

    public TagMagnitudeVector add(TagMagnitudeVector o);

    public TagMagnitudeVector add(Collection<TagMagnitudeVector> tmList);
}
